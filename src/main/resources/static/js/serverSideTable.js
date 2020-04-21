$(document).ready( function () {
    var table = $('#testRecords').DataTable({
        sAjaxSource: '/base/testRecords',
        sAjaxDataProp: 'data',
        processing: true,
        serverSide: true,
        "order": [[ 0, "asc" ]],
        columns: [
            { data: "username"},
            { data: "firstname" },
            { data: "lastname" }
        ]
    })
});