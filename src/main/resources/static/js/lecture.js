const AddLecture = new function () {

    const modalAdd = document.getElementById('add-lecture');
    const addHeader = modalAdd.querySelector('#addLectureHeader');

    this.showAdd = function () {
        modalAdd.style.display = 'block';
        addHeader.textContent = "Add Lecture"
    };
    this.cancelAddLecture = function () {
        modalAdd.style.display = 'none';
        this.close();
    };

    this.confirmAddLecture = function () {
        return true;
    };
};
const DetailsLecture = new function () {

    var id,
        date,
        title,
        description,
        lecturer;

    const modalDetails = document.getElementById('lecture-details');
    const lectureHeader = modalDetails.querySelector('#detailsLectureHeader');

    const detailsLectureId= document.getElementById('detailsLectureId');
    const detailsLectureDate = document.getElementById('detailsLectureDate');
    const detailsLectureTitle = document.getElementById('detailsLectureTitle');
    const detailsLectureDescription = document.getElementById('detailsLectureDescription');
    const detailsLectureLecturer = document.getElementById('detailsLectureLecturer');

    this.showDetails = function (tId, tDate, tTitle, tDescription, tLecturer) {

        id = tId;
        date = tDate;
        title = tTitle;
        description = tDescription;
        lecturer = tLecturer;

        modalDetails.style.display = 'block';
        lectureHeader.textContent = 'Lecture Details';

        detailsLectureId.textContent = id;
        detailsLectureDate.textContent = date;
        detailsLectureTitle.textContent = title;
        detailsLectureDescription.textContent = description;
        detailsLectureLecturer.textContent = lecturer;
    };

    this.hideDetails = function () {
        modalDetails.style.display = 'none';
        this.close();
    };
};

const EditLecture = new function () {
    var id,
        date,
        title,
        description,
        lecturer;

    const modalEdit = document.getElementById('edit-lecture');
    const editHeader = modalEdit.querySelector('#editLectureHeader');

    const editId = document.getElementById('editLectureId');
    const editLectureDate = document.getElementById('editLectureDate');
    const editLectureTitle = document.getElementById('editLectureTitle');
    const editLectureDescription = document.getElementById('editLectureDescription');
    const editLectureLecturer = document.getElementById('editLectureLecturer');

    this.showEdit = function (tId, tDate, tTitle, tDescription, tLecturer) {

        id = tId;
        date = tDate;
        title = tTitle;
        description = tDescription;
        lecturer = tLecturer;

        modalEdit.style.display = 'block';
        editHeader.textContent = 'Edit lecture !!!';
        editId.value = id;
        editLectureDate.value = date;
        editLectureTitle.value = title;
        editLectureDescription.value = description;
        editLectureLecturer.value = lecturer;
    };

    this.cancelEdit = function () {
        modalEdit.style.display = 'none';
        this.close();
    };

    this.confirmEditLecture = function () {
        var url = "/admin/lectures/" + id + "/update";
        document.getElementById('editLectureForm').setAttribute('action', url);
        return true;
    };
};

const DeleteLecture = new function () {
    var id;
    var title;
    const modalDelete = document.getElementById('delete-lecture');
    const deleteHeader = modalDelete.querySelector('#deleteLectureHeader');
    const deleteBody = modalDelete.querySelector('#deleteLectureBody');

    this.showLectureDelete = function (tId, tTitle) {
        id = tId;
        title = tTitle;
        modalDelete.style.display = 'block';
        deleteHeader.textContent = "Delete lecture !!!";
        deleteBody.textContent = "Do you really wont delete lecture " + title + " ?";
    };

    this.deleteLecture = function () {
        var url = "/admin/lectures/" + id + "/delete";
        document.getElementById('deleteLectureForm').setAttribute('action', url);
    };

    this.cancelDeleteLecture = function () {
        modalDelete.style.display = 'none';
        this.close();
    }
};

function searchLectureFunction(){
    var input, filter, table, tr, td0, td2,  i, txtValue0, txtValue2;
    input = document.getElementById("searchLectureInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("lectureTable");
    tr = table.getElementsByTagName("tr");

    for(i = 0; i < tr.length; i++) {
        td0 = tr[i].getElementsByTagName("td")[0];
        td2 = tr[i].getElementsByTagName("td")[2];
        if(td0 || td2) {
            txtValue0 = td0.textContent || td0.innerText;
            txtValue2 = td2.textContent || td2.innerText;
            if(txtValue0.toUpperCase().indexOf(filter) > -1 || txtValue2.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            }else {
                tr[i].style.display = "none";
            }
        }
    }
}