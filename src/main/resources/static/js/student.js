const AddStudent = new function () {

    const modalAdd = document.getElementById('modal-add');
    const addHeader = modalAdd.querySelector('#addHeader');

    this.showAdd = function () {
        modalAdd.style.display = 'block';
        addHeader.textContent = "Add Student"
    };
    this.cancelAddStudent = function () {
        modalAdd.style.display = 'none';
        this.close();
    };

    this.passwordValidate = function () {
        var password = document.getElementById('password');
        var confirmPassword = document.getElementById('confirmPassword');
        if(password.value !== confirmPassword.value){
            confirmPassword.value = "";
            confirmPassword.placeholder = "Confirm password don't match";
            return false;
        }else{
            return true;
        }
    };
};

const EditStudent = new function () {
    var id,
        firstName,
        lastName,
        mail,
        indexNumber,
        studyField,
        studyYear,
        password;

    const modalEdit = document.getElementById('modal-edit');
    const editHeader = modalEdit.querySelector('#editHeader');
    const editId = document.getElementById('editId');
    const editFirstName = document.getElementById('editFirstName');
    const editLastName = document.getElementById('editLastName');
    const editMail = document.getElementById('editMail');
    const editIndexNumber = document.getElementById('editIndexNumber');
    const editStudyField = document.getElementById('editStudyField');
    const editStudyYear = document.getElementById('editStudyYear');
    const editPassword = document.getElementById('editPassword');

    this.showEdit = function (tId, tFirstName, tLastName, tMail,
                              tIndexNumber, tStudyField, tStudyYear, tPassword) {

        id = tId;
        firstName = tFirstName;
        lastName = tLastName;
        mail = tMail;
        indexNumber = tIndexNumber;
        studyField = tStudyField;
        studyYear = tStudyYear;
        password = tPassword;

        modalEdit.style.display = 'block';
        editHeader.textContent = 'Edit account !!!';
        editId.value = id;
        editFirstName.value = firstName;
        editLastName.value = lastName;
        editMail.value = mail;
        editIndexNumber.value = indexNumber;
        editStudyField.value = studyField;
        editStudyYear.value = studyYear;
        editPassword.value = password;
    };

    this.cancelEdit = function () {
        modalEdit.style.display = 'none';
        this.close();
    };

    this.passwordValidate = function () {
        var password = document.getElementById('editPassword');
        var confirmPassword = document.getElementById('confirmEditPassword');
        if(password.value !== confirmPassword.value){
            confirmPassword.value = "";
            confirmPassword.placeholder = "Confirm password don't match";
            return false;
        }else{
            var url = "/admin/students/update/" + id;
            document.getElementById('editForm').setAttribute('action', url);
            return true;
        }
    };
};

const DeleteStudent = new function () {

    var id;
    var name;
    var surname;
    const modalDelete = document.getElementById('modal-delete');
    const deleteHeader = modalDelete.querySelector('#deleteHeader');
    const deleteBody = modalDelete.querySelector('#deleteBody');

    this.showDelete = function (tId, tName, tSurname) {
        id = tId;
        name = tName;
        surname = tSurname;
        modalDelete.style.display = 'block';
        deleteHeader.textContent = "Delete account !!!";
        deleteBody.textContent = "Do you really wont delete student " + name + " " + surname + " ?";
    };

    this.deleteStudent = function () {
        var url = "/admin/students/delete/";
        url = url + id;
        window.location = url;
    };

    this.cancelDelete = function () {
        modalDelete.style.display = 'none';
        this.close();
    }
};