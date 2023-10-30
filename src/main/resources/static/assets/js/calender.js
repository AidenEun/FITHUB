let date = new Date();

const renderCalender = () =>{

    const viewYear = date.getFullYear();
    const viewMonth = date.getMonth();

    const enMonth = ["January","February","March","April","May","June","July",
        "August","September","October","November","December"]

    document.querySelector('.year_month').textContent =  `${enMonth[viewMonth]} ${viewYear} `;


    // 현재연도와 월을 기반으로 이전달의 마지막일자 계산 day0 -> 이전달의 마지막 일자
    const prevLast = new Date(viewYear, viewMonth, 0);
    //이번달의 마지막일자
    const thisLast = new Date(viewYear, viewMonth +1,0);

    const PLDate = prevLast.getDate();
    const PLDay = prevLast.getDay();

    const TLDate = thisLast.getDate();
    const TLDay = thisLast.getDay();

    const prevDates =[];
    const thisDates = [...Array(TLDate + 1).keys()].slice(1);
    const nextDates = [];

    if(PLDay !== 6){
        for(let i = 0; i< PLDay +1; i++){
            prevDates.unshift(PLDate - i);
        }
    }

    for(let i = 1; i<7 - TLDay; i++){
        nextDates.push(i);
    }

    const dates = prevDates.concat(thisDates, nextDates);
    const firstDateIndex = dates.indexOf(1);
    const lastDateIndex = dates.lastIndexOf(TLDate);


    dates.forEach((date,i) => {
        const condition = i>= firstDateIndex && i < lastDateIndex +1
            ? 'this' : 'other';
        dates[i] = `<div class="date"><span class=${condition}>${date}</span></div>`;
    });

    document.querySelector(`.dates`).innerHTML = dates.join('');

    const today = new  Date();
    if(viewMonth === today.getMonth() && viewYear === today.getFullYear()){
        for (let date of document.querySelectorAll('.this')){
            if (+date.innerHTML === today.getDate()){
                date.classList.add(`today`);
                break;
            }
        }
    }
};

renderCalender();

const prevMonth = () =>{
    date.setDate(1);
    date.setMonth(date.getMonth()-1);
    renderCalender();
}

const nextMonth = () =>{
    date.setDate(1);
    date.setMonth(date.getMonth()+1);
    renderCalender();
}

const goToday = () =>{
    date = new Date();
    renderCalender();
}