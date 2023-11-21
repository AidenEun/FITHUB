const challengeButton = document.querySelector(".challengeOpen");
const closeChallenge = document.querySelector(".challengeClose");
const challengeModalBox = document.querySelector('.challenge_modal_box');


$('.challengeOpen').on('click',challengeModal);

closeChallenge.addEventListener("click", () => {
    clearChallengeModalContent();
    challengeModalBox.classList.remove("active");
});

// 모달 외부 클릭 시 모달 창 닫기
challengeModalBox.addEventListener('click', (e) => {
    if (e.target === challengeModalBox) {
        clearChallengeModalContent();
        challengeModalBox.classList.remove("active");
    }
});

const cate = document.querySelectorAll('.cate');
let nowCate = document.querySelector('.now_cate');

cate.forEach(function(btn) {
    btn.addEventListener('click', function() {
        if (btn.classList.contains('now_cate')) {
         return;
        }

        btn.classList.remove('not_cate');
        btn.classList.add('now_cate');

        nowCate.classList.remove('now_cate');
        nowCate.classList.add('not_cate');

        nowCate = btn;
    });
});


function challengeModal(e){
    e.preventDefault();

    challengeModalBox.classList.add("active");

    let category = 'successChall';
    loadPage('/challenge/successChall', 1,category);

    $('#successChall').click(function() {
        category = 'successChall';
        loadPage('/challenge/successChall', 1,category);
    });

    $('#allChall').click(function() {
        category = 'allChall';
        loadPage('/challenge/allChall', 1, category);
    });

}

    // 페이지 데이터를 불러오는 함수
    function loadPage(url, pageNum,category) {
        $.ajax({
            url: url,
            data: { pageNum: pageNum, category: category },
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                // 게시글 목록 업데이트
                if(category === "successChall"){
                    successDisplayData(data);
                } else if(category === "allChall"){
                    displayData(data)
                }

                // 페이징 버튼 업데이트
                displayPaging(data.pageDTO,category);
            },
            error: function (error) {
                console.error('데이터를 가져오는 중 오류 발생: ' + error);
            }
        });
    }

    function displayPaging(pageDTO,category) {
        var startPage = pageDTO.startPage;
        var endPage = pageDTO.endPage;
        var prev = pageDTO.prev;
        var next = pageDTO.next;

        var str = '';

        if (prev) {
            str += '<a class="changePage" href="' + (startPage - 1) + '"><code>&lt;</code></a>';
        }

        for (var i = startPage; i <= endPage; i++) {
            if (i === pageDTO.cri.pagenum) {
                str += '<span class="nowPage">' + i + '</span> ';
            } else {
                str += '<a class="changePage" href="' + i + '">' + i + '</a>';
            }
        }

        if (next) {
            str += '<a class="changePage" href="' + (endPage + 1) + '"><code>&gt;</code></a>';
        }

        var page = $('.pagination');
        page.html('<tr align="center" valign="middle"><td>' + str + '</td></tr>');

        // 페이지 버튼 클릭 이벤트를 연결
        $(".changePage").on("click", function (e) {
            e.preventDefault(); // 기본 동작 중지

            var pageNum = $(this).attr('href');
            loadPage('/challenge/' + category, pageNum);
        });
    }


    function successDisplayData(data) {
        var tableHead = $('#data-table-head');
        tableHead.empty();
        var tableBody = $('#data-table');
        tableBody.empty();


        var headRow = $('<tr align="right" valign="middle">');
        headRow.append('<td colspan="8">글 개수 : '+data.pageDTO.total+'</td>');
        tableHead.append(headRow);

        if (data.list != null && data.list.length > 0) {
            data.list.forEach(function (board) {
                var row = $('<tr>');
                row.append('<td class="long_text">'+ board.mychallNum +'</td>');
                row.append('<td class="long_text">' + board.userId + '</td>');
                row.append('<td class="long_text">' + board.challCategory+ '</td>');
                row.append('<td class="long_text">' + board.challNum + '</td>');
                row.append('<td class="long_text">' + board.challName + '</td>');
                row.append('<td class="long_text">' + board.subRegdate + '</td>');
                row.append('<td class="long_text">' + board.challTerm + ' </td>');
                row.append('<td class="long_text"><a href="'+board.mychallNum+'" class="writeOpen button">선택</a></td>');
                tableBody.append(row);
            });
        }
        else {
            tableBody.append('<tr><td colspan="8">등록된 게시글이 없습니다.</td></tr>');
        }


	    const writeForm = $("#writeForm");
        $(".writeOpen").on("click",function(e){
            e.preventDefault();
            let mychallNum = $(this).attr("href");
            writeForm.append("<input type='hidden' name='mychallNum' value='"+mychallNum+"'>")
            writeForm.attr("method","get");
            writeForm.submit();
        });
    }


function displayData(data) {
        var tableHead = $('#data-table-head');
        tableHead.empty();
        var tableBody = $('#data-table');
        tableBody.empty();


        var headRow = $('<tr align="right" valign="middle">');
        headRow.append('<td colspan="8">글 개수 : '+data.pageDTO.total+'</td>');
        tableHead.append(headRow);

        if (data.list != null && data.list.length > 0) {
            data.list.forEach(function (board) {
                var row = $('<tr>');
                row.append('<td class="long_text">'+ board.mychallNum +'</td>');
                row.append('<td class="long_text">' + board.userId + '</td>');
                row.append('<td class="long_text">' + board.challCategory+ '</td>');
                row.append('<td class="long_text">' + board.challNum + '</td>');
                row.append('<td class="long_text">' + board.challName + '</td>');
                row.append('<td class="long_text">' + board.subRegdate + '</td>');
                row.append('<td colspan="2" class="long_text">' + board.challTerm + '</td>');
                tableBody.append(row);
            });
        }
        else {
            tableBody.append('<tr><td colspan="8">등록된 게시글이 없습니다.</td></tr>');
        }

    }


// 모달 내용 초기화 함수
function clearChallengeModalContent() {
    var tableHead = document.getElementById('data-table-head');
    tableHead.innerHTML = "";
    var tableBody = document.getElementById('data-table');
    tableBody.innerHTML = "";
}






