// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

let dailySummayData = JSON.parse(document.getElementById("dailySummayData").value);
let vals = dailySummayData.vals;
if(vals[0] == 0 && vals[1] == 0 && vals[2] == 0){
  vals[0] = 0.1;
  vals[1] = 0.1;
  vals[2] = 0.1;
  dailySummayData.labels[0] = "未作答";
  dailySummayData.labels[1] = "未作答";
  dailySummayData.labels[2] = "未作答";
}

// Pie Chart Example
var dailySummay = document.getElementById("dailySummay");
var dailySummayChart = new Chart(dailySummay, {
  type: 'pie',
  data: {
    labels: dailySummayData.labels,
    datasets: [{
      data: vals,
      backgroundColor: ['#28a745', '#ffc107', '#dc3545'],
    }],
  },
});
