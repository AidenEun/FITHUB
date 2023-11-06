function agree() {
    const checkAll = document.getElementById('chkAll');
    const chks = document.querySelectorAll('.chk');
    const chkBoxLength = chks.length;

    checkAll.addEventListener('click', function (event) {
        if (event.target.checked) {
            chks.forEach(function (value) {
                value.checked = true;
            });
        } else {
            chks.forEach(function (value) {
                value.checked = false;
            });
        }
    });

    for (chk of chks) {
        chk.addEventListener('click', function () {
            let count = 0;
            chks.forEach(function (value) {
                if (value.checked == true) {
                    count++;
                }
            });
            if (count !== chkBoxLength) {
                checkAll.checked = false;
            } else {
                checkAll.checked = true;
            }
        });
    }
}

function agreeAndSubmit() {
    const isAgreed = document.getElementById('chkAll').checked;

    if (isAgreed) {
        // 사용자가 약관에 동의한 경우, 회원가입 절차 시작
        document.getElementById('joinForm').submit();
    } else {
        alert('약관에 동의해야 회원가입이 가능합니다.');
    }
}