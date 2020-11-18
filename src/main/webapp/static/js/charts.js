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
               categories: expenseTypeCategory,
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
                data: expenseCostCategory,
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
            name: expenseTypeCategory[0],
            y: (expenseCostCategory[0]/totalCostCategory),
            
        }, {
            name: expenseTypeCategory[1],
            y: (expenseCostCategory[1]/totalCostCategory)
        }, {
            name: expenseTypeCategory[2],
            y: (expenseCostCategory[2]/totalCostCategory)
        }, {
            name: expenseTypeCategory[3],
            y: (expenseCostCategory[3]/totalCostCategory)
        }, {
            name: expenseTypeCategory[4],
            y: (expenseCostCategory[4]/totalCostCategory)
        }]
    }]
    });
       });