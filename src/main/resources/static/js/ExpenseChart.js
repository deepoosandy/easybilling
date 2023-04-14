//var barColors = "red";
var baseUrl="http://localhost:8081/chartdata/";
function createChart(selectedYear, chartFor, chartColor, canvasName, chartTitle, chatType) {
var year=new Date().getFullYear();
    if(selectedYear!=null){
    year=selectedYear;
    }
   var url= baseUrl+year+"?source="+chartFor;
    var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               let  jsonData=this.responseText;
                let jsonObj=JSON.parse(jsonData);
                var xAxisData=[];
                var yAxisData=[];
                    for(let i = 0; i < jsonObj.length; i++) {
                        let obj = jsonObj[i];
                        xAxisData.push(obj.month);
                        yAxisData.push(obj.amount);
                    }
                    createExpenseChart(canvasName, xAxisData, yAxisData, chartTitle, year,chatType,chartColor);

            }
    };

    xhttp.open("GET", url, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("Your JSON Data Here");
}

