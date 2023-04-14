function createExpenseChart( canvasId,  xAxis,  yAxis,  title,  graphDataYear, graphType,barColors){
new Chart(canvasId, {
  type: graphType,
  data: {
    labels: xAxis,
    datasets: [{
      backgroundColor: barColors,
      data: yAxis
    }]
  },
  options: {
    legend: {display: false},
    title: {
      display: true,
      text: title+" "+graphDataYear
    }
  }
});
}