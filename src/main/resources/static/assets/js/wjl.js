$(document).ready(function () {
    $('.btn-close').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        $(this).parent().parent().parent().fadeOut();
    });
    $('.btn-minimize').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
        var $target = $(this).parent().parent().next('.box-content');
        if ($target.is(':visible')){
            $('i', $(this)).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        }
        else{
            $('i', $(this)).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
        }
        $target.slideToggle();
    });

    $("div.box-header").click(function(e){
        e.preventDefault();
        e.stopPropagation();
        var $target = $(this).next('.box-content');
        if ($target.is(':visible')){
            $('i', $("a.btn-minimize")).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        }
        else{
            $('i', $("a.btn-minimize")).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
        }
        $target.slideToggle();
    })
});