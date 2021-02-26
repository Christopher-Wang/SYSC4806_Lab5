var createid;
var viewid;
$(document).ready(function() {

    $(document).on("submit", "#createaddressbook", function(event){
               $.ajax({
                  type: $("#createaddressbook").attr("method"),
                  url: '/createaddressbook'
              }).done(function(response) {
                      let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
                      $("body").html(body);
                  });
              event.preventDefault();
          });

    $(document).on("submit", ".returnhome", function(event){
               $.ajax({
                   type: $('.returnhome').attr("method"),
                   url: '/'
               }).done(function(response) {
                       let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
                       $("body").html(body);
                   });
               event.preventDefault();
       });

    $(document).on("submit", "#createbuddyinfo", function(event){
                createid = $("#createid").val();
                let data = {"id": createid};
                $.ajax({
                    type: $('#createbuddyinfo').attr("method"),
                    url: '/createbuddyinfo?' + $.param(data)
                }).done(function(response) {
                        let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
                        $("body").html(body);
                    });
                event.preventDefault();
    });

    $(document).on("submit", "#viewaddressbook", function(event){
                    viewid = $("#viewid").val();
                    let data = {"id": viewid};
                    $.ajax({
                        type: $('#viewaddressbook').attr("method"),
                        url: '/viewaddressbook'
                        data: $('#viewaddressbook').serialize(),
                    }).done(function(response) {
                            let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
                            $("body").html(body);
                        });
                    event.preventDefault();
        });

    $(document).on("submit", "#buddyinfoform", function(event){
            let data = {
                "name": $("#name").val(),
                "number": $("#number").val(),
                "address": $("#address").val(),

            };
            $.ajax({
                type: $('#buddyinfoform').attr("method"),
                url: '/createbuddyinfo/' + createid,
                data: $("#buddyinfoform").serialize()
            }).done(function(response) {
                    let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
                    $("body").html(body);
                });
            event.preventDefault();
    });
});

//$(document).ready(function() {
//    $('#createaddressbook').submit(function(event){
//        $.ajax({
//            type: $('#createaddressbook').attr("method"),
//            url: '/createaddressbook'
//        }).done(function(response) {
//                let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
//                $("div").html(body);
//            });
//        event.preventDefault();
//    });
//
//    $('#createbuddyinfo').submit(function(event){
//            let data = {"id": $("#createid").val()};
//            $.ajax({
//                type: $('#createbuddyinfo').attr("method"),
//                url: '/createbuddyinfo?' + $.param(data)
//            }).done(function(response) {
//                    let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
//                    $("body").html(body);
//                });
//            event.preventDefault();
//    });
//
//    $('#createbuddyinfo').submit(function(event){
//                let data = {
//                    "name": $("#name").val(),
//                    "number": $("#number").val(),
//                    "address": $("#address").val(),
//
//                };
//                $.ajax({
//                    type: $('#createbuddyinfo').attr("method"),
//                    url: '/createbuddyinfo?' + $.param(data)
//                }).done(function(response) {
//                        let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
//                        $("body").html(body);
//                    });
//                event.preventDefault();
//        });
//
//    $('.returnhome').submit(function(event){
//            $.ajax({
//                type: $('.returnhome').attr("method"),
//                url: '/'
//            }).done(function(response) {
//                    let body = response.substring(response.indexOf("<body>")+6,response.indexOf("</body>"));
//                    $("body").html(body);
//                });
//            event.preventDefault();
//    });
//
//
//
//});