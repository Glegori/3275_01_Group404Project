document.addEventListener('DOMContentLoaded', function () {
		var test = [];
		
        var seriesChart = Highcharts.chart('containerCategory', {
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Total Costs Summed By Expense Type'
            },
            xAxis: {
               categories: expenseType,
               title: {
                    text: 'Expense Type'
                }
            },
            yAxis: {
                title: {
                    text: 'Dollars spent'
                }
            },
            series: [{
            	name: 'Total Cost',
                data: expenseCost,
                color: '#0d0d0d'
            }]
        });
        var pieChart = Highcharts.chart('containerPieCategory', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: 'Allocated Cost Percentages Per Expense Type'
    },
    tooltip: {
        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
    },
    accessibility: {
        point: {
            valueSuffix: '%'
        }
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: true,
                format: '<b>{point.name}</b>: {point.percentage:.1f} %'
            }
        }
    },
    series: [{
        name: 'Categories',
        colorByPoint: true,
        data: [{
            name: expenseType[0],
            y: (expenseCost[0]/totalCost),
            
        }, {
            name: expenseType[1],
            y: (expenseCost[1]/totalCost)
        }, {
            name: expenseType[2],
            y: (expenseCost[2]/totalCost)
        }, {
            name: expenseType[3],
            y: (expenseCost[3]/totalCost)
        }, {
            name:expenseType[4],
            y: (expenseCost[4]/totalCost)
        }]
    }]
    });
       });