function test(){
const test= document.getElementById('test');
test.style.display = 'block';
const modalEditPassword = document.getElementById('modal-password');
modalEditPassword.style.display = 'block';
}

const EditStudentPassword = new function () {
    var newPassword;
    var id;
    const modalEditPassword = document.getElementById('modal-password');
    const errorEditPassword = document.getElementById('errorEditPassword');
    const editId = document.getElementById('editId');
    const editPassword = document.getElementById('editPassword');

    this.showEditPassword = function (tId) {

        id = tId;
        editId.value = id;
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
            var newPassword = editPassword.value;
            var url = "/student/edit/password/" + newPassword;
            errorEditPassword.style.display = 'none';
            document.getElementById('editPasswordForm').setAttribute('action', url);
            return true;
        }
    }
};