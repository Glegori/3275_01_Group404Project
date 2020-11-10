document.addEventListener('DOMContentLoaded', function () {
		var test = [];
		
        var myChart = Highcharts.chart('container', {
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Total Costs By Category'
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
    });