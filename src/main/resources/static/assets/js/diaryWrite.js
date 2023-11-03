function weighsum(e){

    const weightGoalNum = document.querySelector("#weightGoal").value;
    const weightGoal = weightGoalNum.slice(0, -2);

    if(48 <= window.event.keyCode && window.event.keyCode <= 57 || window.event.keyCode == 8){


        const todayweigh = document.querySelector("#todayweigh");

        let resultweigh = Number(weightGoal)-Number(todayweigh.value);

        document.querySelector("#resultweigh").value = resultweigh;
    }
    else{
        alert("숫자만 입력하세요")
        document.querySelector("#todayweigh").value = "";

    }

}