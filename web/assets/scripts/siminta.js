//
///*====================================
// Free To Use For Personal And Commercial Usage
//Author: http://binarytheme.com
// Share Us if You Like our work 
// Enjoy Our Codes For Free always.
//======================================*/
//
//$(function () {
//    // tooltip demo
//    $('.tooltip-demo').tooltip({
//        selector: "[data-toggle=tooltip]",
//        container: "body"
//    })
//
//    // popover demo
//    $("[data-toggle=popover]")
//        .popover()
//    ///calling side menu
//
//    $('#side-menu').metisMenu();
//
//    ///pace function for showing progress
//
//    function load(time) {
//        var x = new XMLHttpRequest()
//        x.open('GET', "" + time, true);
//        x.send();
//    };
//
//    load(20);
//    load(100);
//    load(500);
//    load(2000);
//    load(3000);
//    setTimeout(function () {
//        Pace.ignore(function () {
//            load(3100);
//        });
//    }, 4000);
//
//    Pace.on('hide', function () {
//        console.log('done');
//    });
//    paceOptions = {
//        elements: true
//    };
//   
//
//});
//
////Loads the correct sidebar on window load, collapses the sidebar on window resize.
//$(function() {
//    $(window).bind("load resize", function() {
//        console.log($(this).width())
//        if ($(this).width() < 768) {
//            $('div.sidebar-collapse').addClass('collapse')
//        } else {
//            $('div.sidebar-collapse').removeClass('collapse')
//        }
//    })
//})
//
//$(function() {
//  $(".search").keyup(function () {
//    var searchTerm = $(".search").val();
//    var listItem = $('.results tbody').children('tr');
//    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")
//    
//  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
//        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
//    }
//  });
//    
//  $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
//    $(this).attr('visible','false');
//  });
//
//  $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e){
//    $(this).attr('visible','true');
//  });
//
//  var jobCount = $('.results tbody tr[visible="true"]').length;
//    $('.counter').text(jobCount + ' item');
//
//  if(jobCount == '0') {$('.no-result').show();}
//    else {$('.no-result').hide();}
//		  });
//});
