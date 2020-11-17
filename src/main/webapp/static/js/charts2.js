document.addEventListener('DOMContentLoaded', function () {
		var test = [];
		
        var myChart = Highcharts.chart('containerUser', {
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Total Costs Summed By User'
            },
            xAxis: {
               categories: expenseUser,
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