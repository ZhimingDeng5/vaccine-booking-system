if (typeof jQuery === "undefined") {
    throw new Error("jQuery plugins need to be before this file");
}
$(function() {

    "use strict"; 
    $(document).ready(function(){
        $('.owl-carouselthree').owlCarousel({
            loop:true,
            margin:15,
            autoplay:true,
            autoplayTimeout:4000,
            autoplayHoverPause:true,
            nav:true,
            navText: ["<img src='../dist/assets/images/stylish-left.png'>","<img src='../dist/assets/images/stylish-right.png'>"],   
            responsive:{
                0:{
                    items:1
                },
                480:{
                    items:2
                },
                600:{
                    items:2
                },
                1000:{
                    items:3
                },
                1100:{
                    items:3
                },
                1400:{
                    items:3
                }
            }
        })
    });

    // circle gradient
     $(document).ready(function() {
        var options = {
            chart: {
                height: 250,
                type: 'radialBar',
                toolbar: {
                    show: false
                }
            },
            colors:  ['var(--chart-color5)'],
            plotOptions: {
                radialBar: {
                    startAngle: -135,
                    endAngle: 225,
                        hollow: {
                        margin: 0,
                        size: '70%',
                        background: '#fff',
                        image: undefined,
                        imageOffsetX: 0,
                        imageOffsetY: 0,
                        position: 'front',

                        dropShadow: {
                            enabled: true,
                            top: 3,
                            left: 0,
                            blur: 4,
                            opacity: 0.24
                        }
                    },
                    track: {
                        background: '#fff',
                        strokeWidth: '67%',
                        margin: 0, // margin is in pixels
                        dropShadow: {
                            enabled: true,
                            top: -3,
                            left: 0,
                            blur: 4,
                            opacity: 0.35
                        }
                    },

                    dataLabels: {
                        showOn: 'always',
                        name: {
                            offsetY: -10,
                            show: true,
                            color: '#888',
                            fontSize: '17px'
                        },
                        value: {
                            formatter: function(val) {
                                return parseInt(val);
                            },
                            color: '#111',
                            fontSize: '36px',
                            show: true,
                        }
                    }
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shade: 'dark',
                    type: 'horizontal',
                    shadeIntensity: 0.5,
                    gradientToColors: ['var(--chart-color4)'],
                    inverseColors: true,
                    opacityFrom: 1,
                    opacityTo: 1,
                    stops: [0, 100]
                }
            },
            series: [75],
            stroke: {
                lineCap: 'round'
            },
            labels: ['Kcal Left'],
        }

        var chart = new ApexCharts(
            document.querySelector("#apex-circle-gradient"),
            options
        );

        chart.render();    
    });
    
        
});

// function getChart(id){ 
//     var dom = document.getElementById(id);
//     return echarts.init(dom);
// }

