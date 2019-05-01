
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

const AddStudent = new function () {

  const modalAdd = document.getElementById('modal-add');
  const addHeader = modalAdd.querySelector('#addHeader');
  
  this.showAdd = function () {
      modalAdd.style.display = 'block';
      addHeader.textContent = "Add Student"
  };

  this.addStudent = function () {
      window.location = "/admin/students/add";
  };

  this.cancelAddStudent = function () {
      modalAdd.style.display = 'none';
      this.close();
  }
};


