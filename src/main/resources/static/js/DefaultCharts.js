function defaultChart(){
var currentYear=new Date().getFullYear();
createChart(currentYear, "E", "red", "expenseChart", "Expense Data for", "bar") ;
createChart(currentYear, "M", "blue", "mistriChart", "Mistri Data for", "bar");
createChart(currentYear, "C", "green", "cementChart", "Cement Data for", "bar");
createChart(currentYear, "T", "orange", "totalExpenseChart", "Total Expense Data for", "bar");
}

function yearWiseGraph(year){

var currentYear=new Date().getFullYear();
if(year.value!=null){
 currentYear=year.value;
}

createChart(currentYear, "E", "red", "expenseChart", "Expense Data for", "bar") ;
createChart(currentYear, "M", "blue", "mistriChart", "Mistri Data for", "bar");
createChart(currentYear, "C", "green", "cementChart", "Cement Data for", "bar");
createChart(currentYear, "T", "orange", "totalExpenseChart", "Total Expense Data for", "bar");

}

function chartWiseGraph(chartType, year){

var currentYear=year;
createChart(currentYear, "E", "red", "expenseChart", "Expense Data for", chartType.value) ;
createChart(currentYear, "M", "blue", "mistriChart", "Mistri Data for", chartType.value);
createChart(currentYear, "C", "green", "cementChart", "Cement Data for", chartType.value);
createChart(currentYear, "T", "orange", "totalExpenseChart", "Total Expense Data for", "bar");
}
