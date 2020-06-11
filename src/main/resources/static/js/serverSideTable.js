$(document).ready( function () {
    $('#testRecords').DataTable({
        ajax: {
            url: '/base/testRecords',
            contentType: 'application/json',
            type: 'GET',
            data: function (d) {
                // console.log(d);
                return JSON.stringify( d );
            }
        },
        columns: [
            { data: "username", title : 'username'},
            { data: "firstname", title: 'firstname' },
            { data: "lastname", title: 'lastname' }
        ]
    })
});