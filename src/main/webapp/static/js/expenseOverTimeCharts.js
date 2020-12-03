document.addEventListener('DOMContentLoaded',function (){
        console.log(expensesPersonalArrayCosts);
        console.log(expensesPersonalArrayDates);
        console.log(startDate);
        console.log(endDate);

    var date1 = Date.parse(startDate);
    var date2 = Date.parse(endDate);
    var date3 = Date.now();
    console.log(date1);

    var lineChart = Highcharts.chart('container', {
        title: {
            text: 'Chart of Expenses per Expense Type over the selected dates'
        },

        subtitle: {
            text: 'Start: ' + startDate + ' End: ' + endDate
        },

        yAxis: {
            title: {
                text: 'Dollar Spent'
            }
        },

        xAxis: {
            type: 'datetime',
            title: {
                text: 'Date'
            },
            accessibility: {
                rangeDescription: 'Range: ' + date1 + ' to ' + date2
            }
        },

        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        plotOptions: {
            series: {
                marker:{enabled: true},
                label: {
                    connectorAllowed: false
                },
                pointStart: date1

            }
        },

        series: [
            {
                name: 'Personal',
                data: chartDataPersonal
            },
            {
                name: 'Food',
                data: chartDataFood
            },
            {
                name: 'Services',
                data: chartDataServices
            },
            {
                name: 'Travel',
                data: chartDataTravel
            },
            {
                name: 'Suppliers',
                data: chartDataSuppliers
            },




        ],

        responsive: {
            rules: [{
                condition: {
                    maxWidth: 500
                },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }


    });

    // var scatterChart = Highcharts.chart('container2', {
    //     chart:{
    //         type: 'scatter',
    //         zoomType: 'xy'
    //     },
    //     title: {
    //         text: 'Chart of Expenses per Expense Type over the selected dates'
    //     },
    //
    //     subtitle: {
    //         text: 'Start: ' + startDate + ' End: ' + endDate
    //     },
    //
    //     yAxis: {
    //         title: {
    //             text: 'Dollar Spent'
    //         }
    //     },
    //
    //     xAxis: {
    //         type: 'datetime',
    //         title: {
    //             enabled: true,
    //             text: 'Date'
    //         },
    //         startOnTick: true,
    //         endOnTick: true,
    //         showLastLabel: true,
    //         accessibility: {
    //             rangeDescription: 'Range: ' + date1 + ' to ' + date2
    //         }
    //     },
    //     legend: {
    //         layout: 'vertical',
    //         align: 'left',
    //         verticalAlign: 'top',
    //         x: 100,
    //         y: 70,
    //         floating: true,
    //         backgroundColor: Highcharts.defaultOptions.chart.backgroundColor,
    //         borderWidth: 1
    //     },
    //
    //     plotOptions: {
    //         scatter: {
    //             marker: {
    //                 radius: 5,
    //                 states: {
    //                     hover: {
    //                         enabled: true,
    //                         lineColor: 'rgb(100,100,100)'
    //                     }
    //                 }
    //             },
    //             states: {
    //                 hover: {
    //                     marker: {
    //                         enabled: false
    //                     }
    //                 }
    //             },
    //             tooltip: {
    //                 headerFormat: '<b>{series.name}</b><br>',
    //                 pointFormat: '{point.x} , {point.y} dollars'
    //             }
    //         }
    //     },
    //
    //     series: [
    //         {
    //             name: 'Personal',
    //             data: chartDataPersonal
    //         },
    //         {
    //             name: 'Food',
    //             data: chartDataFood
    //         },
    //         {
    //             name: 'Services',
    //             data: chartDataServices
    //         },
    //         {
    //             name: 'Travel',
    //             data: chartDataTravel
    //         },
    //         {
    //             name: 'Suppliers',
    //             data: chartDataSuppliers
    //         },
    //
    //
    //
    //
    //     ],
    //
    //     responsive: {
    //         rules: [{
    //             condition: {
    //                 maxWidth: 500
    //             },
    //             chartOptions: {
    //                 legend: {
    //                     layout: 'horizontal',
    //                     align: 'center',
    //                     verticalAlign: 'bottom'
    //                 }
    //             }
    //         }]
    //     }
    //
    //
    // });

    var areaChart = Highcharts.chart('container2', {
        chart:{
            type:'area'
        },
        title: {
            text: 'Chart of Expenses per Expense Type over the selected dates'
        },

        accessibility: {
            description: 'Image description: An area chart compares the expenses over the selected date.'
        },
        subtitle: {
            text: 'Start: ' + startDate + ' End: ' + endDate
        },

        yAxis: {
            title: {
                text: 'Dollar Spent'
            },
            labels: {
                formatter: function () {
                    return this.value / 1000 + 'k';
                }
            }
        },

        xAxis: {
            type: 'datetime',
            // labels: {
            //     formatter: function () {
            //         return this.value; // clean, unformatted number for year
            //     }
            // },
            title: {
                text: 'Date'
            },
            accessibility: {
                rangeDescription: 'Range: ' + date1 + ' to ' + date2
            }
        },

        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle'
        },

        plotOptions: {
            area: {
                pointStart: date1,
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 2,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },

        series: [
            {
                name: 'Personal',
                data: chartDataPersonal
            },
            {
                name: 'Food',
                data: chartDataFood
            },
            {
                name: 'Services',
                data: chartDataServices
            },
            {
                name: 'Travel',
                data: chartDataTravel
            },
            {
                name: 'Suppliers',
                data: chartDataSuppliers
            },




        ],

        responsive: {
            rules: [{
                condition: {
                    maxWidth: 500
                },
                chartOptions: {
                    legend: {
                        layout: 'horizontal',
                        align: 'center',
                        verticalAlign: 'bottom'
                    }
                }
            }]
        }


    });

});


