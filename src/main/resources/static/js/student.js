const AddStudent = new function () {

    const modalAdd = document.getElementById('modal-add');
    const addHeader = modalAdd.querySelector('#addHeader');
    const errorAddPassword = document.getElementById('errorAddPassword');

    this.showAdd = function () {
        modalAdd.style.display = 'block';
        addHeader.textContent = "Add Student"
    };
    this.cancelAddStudent = function () {
        modalAdd.style.display = 'none';
        errorAddPassword.style.display = 'none';
        this.close();
    };

    this.passwordValidate = function () {
        var password = document.getElementById('password');
        var confirmPassword = document.getElementById('confirmPassword');
        if(password.value !== confirmPassword.value){
            errorAddPassword.style.color = 'red';
            errorAddPassword.style.display = 'block';
            confirmPassword.value = "";
            return false;
        }else{
            errorAddPassword.style.display = 'none';
            return true;
        }
    };
};
const DetailsStudent = new function () {

    var id,
        firstName,
        lastName,
        mail,
        indexNumber,
        studyField,
        studyYear,
        password;

    const modalDetails = document.getElementById('modal-details');
    const detailsHeader = modalDetails.querySelector('#detailsHeader');
    const detailsId = document.getElementById('detailsId');
    const detailsFirstName = document.getElementById('detailsFirstName');
    const detailsLastName = document.getElementById('detailsLastName');
    const detailsMail = document.getElementById('detailsMail');
    const detailsIndexNumber = document.getElementById('detailsIndexNumber');
    const detailsStudyField = document.getElementById('detailsStudyField');
    const detailsStudyYear = document.getElementById('detailsStudyYear');
    const detailsPassword = document.getElementById('detailsPassword');

    this.showDetails = function (tId, tFirstName, tLastName, tMail,
                              tIndexNumber, tStudyField, tStudyYear, tPassword) {

        id = tId;
        firstName = tFirstName;
        lastName = tLastName;
        mail = tMail;
        indexNumber = tIndexNumber;
        studyField = tStudyField;
        studyYear = tStudyYear;
        password = tPassword;

        modalDetails.style.display = 'block';
        detailsHeader.textContent = 'Student Details';
        detailsId.textContent = id;
        detailsFirstName.textContent = firstName;
        detailsLastName.textContent = lastName;
        detailsMail.textContent = mail;
        detailsIndexNumber.textContent = indexNumber;
        detailsStudyField.textContent = studyField;
        detailsStudyYear.textContent = studyYear;
        detailsPassword.textContent = password;
    };

    this.hideDetails = function () {
        modalDetails.style.display = 'none';
        this.close();
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
    const errorEditPassword = document.getElementById('errorEditPassword');

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
        errorEditPassword.style.display = 'none';
        this.close();
    };

    this.passwordValidate = function () {
        var password = document.getElementById('editPassword');
        var confirmPassword = document.getElementById('confirmEditPassword');
        if(password.value !== confirmPassword.value){
            errorEditPassword.style.color = 'red';
            errorEditPassword.style.display = 'block';
            confirmPassword.value = "";
            return false;
        }else{
            var url = "/admin/students/" + id + "/update";
            document.getElementById('editForm').setAttribute('action', url);
            errorEditPassword.style.display = 'none';
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
        var url = "/admin/students/" + id + "/delete";
        document.getElementById('deleteForm').setAttribute('action', url);
    };

    this.cancelDelete = function () {
        modalDelete.style.display = 'none';
        this.close();
    }
};

function searchFunction(){
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("studentTable");
    tr = table.getElementsByTagName("tr");

    for(i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
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

