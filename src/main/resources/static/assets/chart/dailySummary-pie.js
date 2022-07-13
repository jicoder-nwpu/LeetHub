// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
var dailySummay = document.getElementById("dailySummay");
var dailySummayChart = new Chart(dailySummay, {
  type: 'pie',
  data: {
    labels: ["Easy", "Medium", "Hard"],
    datasets: [{
      data: [1, 2, 3],
      backgroundColor: ['#28a745', '#ffc107', '#dc3545'],
    }],
  },
});
