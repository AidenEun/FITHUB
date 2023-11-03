const opensearchButtons = document.querySelectorAll(".opensearch");
const searchclose = document.querySelector(".searchclose");
const searchModalBox = document.querySelector('.searchModalBox');

opensearchButtons.forEach(opensearchButtons => {
    opensearchButtons.addEventListener("click", () => {
        searchModalBox.classList.add("active");
    });
});

searchclose.addEventListener("click", () => {
    searchModalBox.classList.remove("active");
});


function calsearch(){

    const search_input = document.getElementById("search_bar");
    const search_keyword = search_input.value;

    // console.log(search_input);
    console.log(search_keyword);

    const category = document.querySelector('input[name="searchCategory"]:checked').value;

    console.log(category);

    if(category === "food"){
        console.log("음식으로 보내기");
        if(search_keyword){
            $.ajax({

            })
        }
    }
    else {
        console.log("운동으로 보내기");
    }

}