function test(){
const test= document.getElementById('test');
test.style.display = 'block';
const modalEditPassword = document.getElementById('modal-password');
modalEditPassword.style.display = 'block';
}

const EditStudentPassword = new function () {
    var newPassword;
    const modalEditPassword = document.getElementById('modal-password');
    const errorEditPassword = document.getElementById('errorEditPassword');
    const editId = document.getElementById('editId');
    const editPassword = document.getElementById('editPassword');

    this.showEditPassword = function () {
        modalEditPassword.style.display = 'block';
    }

    this.cancelEditPassword = function () {
        modalEditPassword.style.display = 'none';
        errorEditPassword.style.display = 'none';
        this.close();
    }

    this.passwordValidate = function () {
        var editPassword = document.getElementById('editPassword');
        var confirmEditPassword = document.getElementById('confirmEditPassword');
        if(editPassword.value !== confirmEditPassword.value){
            errorEditPassword.style.color = 'red';
            errorEditPassword.style.display = 'block';
            confirmEditPassword.value = "";
            return false;
        }else{
            var url = "/student/edit/password";
            errorEditPassword.style.display = 'none';
            document.getElementById('editPasswordForm').setAttribute('action', url);
            return true;
        }
    }
};

const UpdateAttendance = new function () {
    var attendanceId;
    var lectureId;

    const modalUpdateAttendance = document.getElementById("modal-updateAttendance");
    const updateAttendanceHeader = modalUpdateAttendance.querySelector('#updateAttendanceHeader');
    const updateAttendanceForm = document.getElementById('updateAttendanceForm');
    const userId = document.getElementById('userId').innerHTML;

    this.showUpdateAttendance = function (tLectureId) {

        lectureId = tLectureId;
        modalUpdateAttendance.style.display = 'block';
        updateAttendanceHeader.textContent = "Attendance" + userId;
    };

    this.present = function () {
        var url = "/student/attendances/" + lectureId + "/present";
        updateAttendanceForm.setAttribute('action', url);
    };

    this.absent = function () {
        var url = "/student/attendances/" + lectureId + "/absent";
        updateAttendanceForm.setAttribute('action', url);
    };

    this.cancelUpdate = function () {
        modalUpdateAttendance.style.display = 'none';
        this.close();
    };

};

function checkStudentAttendance() {
    var compareList = [];
    const compareTab = document.getElementById("compareTab");
    const compareTr = compareTab.getElementsByTagName("tr");
    const lectureTable = document.getElementById("lectureTable");
    const button = lectureTable.getElementsByTagName("button");

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