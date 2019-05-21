
function test(x) {
    var table, tr, td, trIndex, tdIndex, c;
    table = document.getElementById("attendanceTable");
    tr = table.getElementsByTagName("tr");
    td = table.getElementsByTagName("td");
    trIndex = x.parentNode.rowIndex;
    tdIndex = x.cellIndex;

    table.rows[2].cells[2].style.backgroundColor = "red";

    document.getElementById("tr").innerHTML = "Row index is: " + trIndex;
    document.getElementById("td").innerHTML = "Column index is: " + tdIndex;

}

function checkStudentAttendance() {
    var compareList = [];
    const compareTab = document.getElementById("compareTab");
    const compareTr = compareTab.getElementsByTagName("tr");
    const attendanceTable = document.getElementById("attendanceTable");
    const button = attendanceTable.getElementsByTagName("button");

    Array.prototype.contain = function (element) {
        return this.indexOf(element) > -1;
    };

    for(var k=0; k < compareTr.length; k++ ){
        var compareStr = compareTab.rows[k].cells[0].innerHTML;
        compareList.push(compareStr);
    }
    for(var i=0; i < button.length; i++){
        var str = button[i].innerText;
        if(compareList.contain(str)){
            button[i].style.backgroundImage = "url('../png/checkmark.png')";
        }
    }
}

function searchAttendanceFunction(){
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchAttendanceInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("attendanceTable");
    tr = table.getElementsByTagName("tr");

    for(i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if(td) {
            txtValue = td.textContent || td.innerText;
            if(txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            }else {
                tr[i].style.display = "none";
            }
        }
    }
}