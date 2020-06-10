$(document).ready( function () {
    var table = $('#shortstorage').DataTable({
        ajax: '/base/getcompany',
        sAjaxDataProp: 'data',
        processing: true,
        serverSide: true,
        // "order": [[ 0, "asc" ]],
        columns: [
            { data: "id", title : 'id'},
            { data: "name", title: 'name' },
            { data: "shortname", title: 'shortname' }
        ]
    })
});