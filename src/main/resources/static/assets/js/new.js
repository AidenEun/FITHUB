ScrollOut({
once:true

});
const logo_box = document.querySelector('#logo_box');
const wrap =  document.querySelector('.wrap');
logo_box.addEventListener("click", () => {
    logo_box.style.opacity = 0;

    setTimeout(function() {
        logo_box.classList.add('.none');
        wrap.style.display = "block";
    },700);

});

document.addEventListener('click', function(event) {
    if (!logo_box.contains(event.target)) {
        logo_box.style.opacity = 0;

        setTimeout(function() {
            logo_box.classList.add('.none');
            wrap.style.display = "block";
        },700);
    }
  });
document.addEventListener('DOMContentLoaded', ()=>{
    new TypeIt('#title', {afterComplete: function(instance){
        instance.destroy();
    }
})
    .type('운동의 시작')
    .pause(1000)
    .delete(6, {delay:1000})
    .type('다이어트의 시작')
    .pause(1000)
    .delete(8,{delay:1150})
    .type('Fit Hub')
    .go();
    });
setTimeout(function() {
        // 지연 후 실행할 코드 작성
    const logo_box = document.querySelector('#logo_box');
    const wrap =  document.querySelector('.wrap');

    logo_box.style.left = "24px";
    logo_box.style.top = "27px";
    logo_box.style.fontSize = "13px";

    setTimeout(function() {
        logo_box.classList.add('.none');
        wrap.style.display = "block";
    },900);

}, 7900); 



const one_button1 = document.querySelector('.one_button1');
const one_button2 = document.querySelector('.one_button2');
const one_box1_inner1 = document.querySelector('.one_box1_inner1');
const one_box1_inner2 = document.querySelector('.one_box1_inner2');
const one_content1 = document.querySelector('.section_box .content');
one_button1.addEventListener("click", () => {
    one_box1_inner1.style.opacity=0;
    setTimeout(function() {
        one_box1_inner1.style.display="none";
        one_box1_inner2.style.display="block";
        
        one_content1.style.width = "185%";
        one_content1.style.height = "580px";
        setTimeout(function() {
            one_box1_inner2.style.opacity=1;

        },700);
    },1000);

});
one_button2.addEventListener("click", () => {
    one_box1_inner2.style.opacity=0;
    setTimeout(function() {
        one_box1_inner2.style.display="none";
        one_box1_inner1.style.display="block";
        
        one_content1.style.width = "calc(33.3333333% - 1.25em)";
        one_content1.style.height = "490px";
        setTimeout(function() {
            one_box1_inner1.style.opacity=1;
        },700);
    },1000);

});


var swiper_one = new Swiper(".slide_one", {
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
      
    },
  });

  var swiper_two = new Swiper(".slide_two", {
    scrollbar: {
        el: ".swiper-scrollbar",
        hide: true,
      },

  });


const three_ul_a1 = document.querySelector('.three_ul_a1');
const three_ul_a2 = document.querySelector('.three_ul_a2');
const three_ul_a3 = document.querySelector('.three_ul_a3');
const three_ul_a4 = document.querySelector('.three_ul_a4');
const three_ul_a5 = document.querySelector('.three_ul_a5');
const three_section_div_box = document.querySelector('.three_section_div_box');
let timeout = null;
const resetAndAnimate = (target, left, top) => {
    clearTimeout(timeout);
    three_section_div_box.style.opacity = 0;
    target.style.border = "0px";
    [three_ul_a1, three_ul_a2, three_ul_a3, three_ul_a4, three_ul_a5].forEach(item => {
        if (item !== target) {
            item.style.left = 0;
            item.style.top = 0;
            item.classList.remove("fontSize_24px");

        }
    });
    timeout = setTimeout(() => {
        three_section_div_box.style.opacity = 1;
        [three_ul_a1, three_ul_a2, three_ul_a3, three_ul_a4, three_ul_a5].forEach(item => {
            if (item !== target) {
                item.style.border = "1px solid black";

            }
        });
        target.style.left = left;
        target.style.top = top;
        target.classList.add("fontSize_24px");

    }, 400);
};

three_ul_a1.addEventListener("click", () => resetAndAnimate(three_ul_a1, "40%", "83px"));
three_ul_a2.addEventListener("click", () => resetAndAnimate(three_ul_a2, "20%", "83px"));
three_ul_a3.addEventListener("click", () => resetAndAnimate(three_ul_a3, 0, "83px"));
three_ul_a4.addEventListener("click", () => resetAndAnimate(three_ul_a4, "-20%", "83px"));
three_ul_a5.addEventListener("click", () => resetAndAnimate(three_ul_a5, "-40%", "83px"));



var slides = document.querySelector('.slides'),
        slide = document.querySelectorAll('.slides li'),
        currentIdx = 0,
        slideCount = slide.length,
        slideWidth = 270,
        slideMargin = 0;
        // prevBtn = document.querySelector('.prev'),
        // nextBtn = document.querySelector('.next');

    makeClone();

    function makeClone(){
        for(var i = 0; i <slideCount; i++){
            var cloneSlide = slide[i].cloneNode(true);
            cloneSlide.classList.add('clone');
            slides.appendChild(cloneSlide);
        }
        for(var i = slideCount -1; i>=0; i--){
            var cloneSlide = slide[i].cloneNode(true);
            cloneSlide.classList.add('clone');
            slides.prepend(cloneSlide);
        }
        updateWidth();
        setInitialPos();
        setTimeout(function(){
            slides.classList.add('animated');
        },100);
    }

    function updateWidth(){
        var currentSlides = document.querySelectorAll('.slides li');
        var newSlideCount = currentSlides.length;

        var newWidth = (slideWidth + slideMargin)*newSlideCount - slideMargin + 'px';
        slides.style.width = newWidth;
    }   

    function setInitialPos(){
        var initialTranslateValue = -(slideWidth + slideMargin)*slideCount;
        slides.style.transform= 'translateX(' + initialTranslateValue+'px)';
        
    }

    // nextBtn.addEventListener('click',function(){
    //     moveSlide(currentIdx + 1);
    // });
    // prevBtn.addEventListener('click',function(){
    //     moveSlide(currentIdx - 1);
    // });

    var timer = undefined;

    function autoSlide(){
        if(timer == undefined){
            timer = setInterval(function(){
                moveSlide(currentIdx + 1);
            },4000);
        }
    }
    autoSlide();
    function stopSlide(){
        clearInterval(timer);
        timer = undefined;

    }
    slides.addEventListener('mouseenter',function(){
        stopSlide();
    });
    slides.addEventListener('mouseleave',function(){
        autoSlide();
    });


    function moveSlide(num){
        slides.style.left = - num * (slideWidth + slideMargin) + 'px';
        currentIdx = num;
        if(currentIdx == slideCount || currentIdx == - slideCount){
            setTimeout(function(){
                slides.classList.remove('animated');
                slides.style.left = '0px';
                currentIdx = 0;
            },700);
            setTimeout(function(){
                slides.classList.add('animated');
            },800);
        }
    }


//const three_section_div = document.querySelector('.three_section_div');
//const height_max = document.querySelector('.three_section_div_2');
//height_max.addEventListener('click',() =>{
//    // height_max.style.maxHeight += heightMax;
//    let heightMax = 1400;
//    let height_max_idx = height_max.id*1;
//    height_max.id = height_max_idx + 1;
//    height_max.style.maxHeight = heightMax*height_max.id + 'px';
//});

const three_swiper_slide = document.querySelector('.three_swiper_slide');
const four_swiper_slide = document.querySelector('.four_swiper_slide');
const three_section_td = document.querySelector('.three_section_td');

let observer = new IntersectionObserver((entries) => {
    clearTimeout(timeout);
    console.log(entries[0]);
    if (entries[0].isIntersecting && entries[0].intersectionRatio >= 0.15) {
        wrap.classList.add('transition');
        timeout = setTimeout(function(){
            wrap.style.background = "#ebede1";
        },100);
    } else {
        wrap.style.background = "#fff";
        timeout = setTimeout(function(){
            wrap.classList.remove('transition');
        },1000);
    }
}, { threshold: 0.15 });

observer.observe(three_section_td);