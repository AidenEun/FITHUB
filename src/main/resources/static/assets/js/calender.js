let date = new Date();

const renderCalender = () => {

    const viewYear = date.getFullYear();
    const viewMonth = date.getMonth();

    const enMonth = ["January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December"]

    document.querySelector('.year_month').textContent = `${enMonth[viewMonth]} ${viewYear} `;


    // 현재연도와 월을 기반으로 이전달의 마지막일자 계산 day0 -> 이전달의 마지막 일자
    const prevLast = new Date(viewYear, viewMonth, 0);
    //이번달의 마지막일자
    const thisLast = new Date(viewYear, viewMonth + 1, 0);

    const PLDate = prevLast.getDate();
    const PLDay = prevLast.getDay();

    const TLDate = thisLast.getDate();
    const TLDay = thisLast.getDay();

    const prevDates = [];
    const thisDates = [...Array(TLDate + 1).keys()].slice(1);
    const nextDates = [];

    if (PLDay !== 6) {
        for (let i = 0; i < PLDay + 1; i++) {
            prevDates.unshift(PLDate - i);
        }
    }

    for (let i = 1; i < 7 - TLDay; i++) {
        nextDates.push(i);
    }

    const dates = prevDates.concat(thisDates, nextDates);
    const firstDateIndex = dates.indexOf(1);
    const lastDateIndex = dates.lastIndexOf(TLDate);


    dates.forEach((date, i) => {
        const condition = i >= firstDateIndex && i < lastDateIndex + 1
            ? 'this' : 'other';
        if (condition === 'this') {
            // dates[i] = `<div class="date" onclick="choicedate(${viewYear},${viewMonth},${date})"><div class="hasdiary"></div><span class=${condition}>${date}</span></div>`;
            dates[i] = `<div class="date" onclick="choicedate(${viewYear},${viewMonth},${date})"><span class=${condition}>${date}</span></div>`;
        }
        else {
            dates[i] = `<div class="date" ><span class=${condition}>${date}</span></div>`;
        }
    });


    document.querySelector(`.dates`).innerHTML = dates.join('');

    const today = new Date();
    if (viewMonth === today.getMonth() && viewYear === today.getFullYear()) {
        for (let date of document.querySelectorAll('.this')) {
            if (+date.innerHTML === today.getDate()) {
                date.classList.add(`today`);
                break;
            }
        }
    }
};

renderCalender();

// let day = new Date();

function choicedate(viewYear, viewMonth, date) {


    let viewrealMonth = viewMonth + 1;
    if (date < 10) date = "0" + date;
    if (viewrealMonth < 10) viewrealMonth = "0" + viewrealMonth;
    // $("#click_date").val(viewYear+"-"+(viewrealMonth)+"-"+date);

    var today = new Date();
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);
    var todaydateString = year + '-' + month  + '-' + day;

    const chociedate = viewYear + "-" + (viewrealMonth) + "-" + date;
    if(chociedate <= todaydateString){
        // console.log("과거");
        location.replace("/usermypage/checklist?choicedate=" + viewYear + "-" + viewrealMonth + "-" + date);
    }
    else {
        // console.log("미래");
        alert("다이어리를 미리 작성할 수 없습니다.\n내일 다시 시도해 주세요!")
    }
    // console.log("클릭한 날짜:"+chociedate);
    // console.log("오늘 날짜"+todaydateString);
    // console.log(viewYear + "-" + (viewrealMonth) + "-" + date);
}

const prevMonth = () => {
    date.setDate(1);
    date.setMonth(date.getMonth() - 1);
    renderCalender();
}

const nextMonth = () => {
    date.setDate(1);
    date.setMonth(date.getMonth() + 1);
    renderCalender();
}

const goToday = () => {
    date = new Date();
    renderCalender();
}