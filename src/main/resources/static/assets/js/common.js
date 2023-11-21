
$("#searchForm a").on("click",sendit);
function sendit(){
    if(!searchForm.find("option:selected").val()){
        alert("검색 기준을 선택하세요!");
        return false;
    }
    if(!document.getElementById("keyword").value){
        alert("키워드를 입력하세요!");
        return false;
    }
    searchForm.submit();
}