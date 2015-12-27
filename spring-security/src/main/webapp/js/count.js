function countUp(_this){
	var count = _this.data("value");
    var div_by = 100,
        speed = Math.round(count / div_by),
        run_count = 1,
        int_speed = 24;

    var int = setInterval(function() {
        if(run_count < div_by){
        	_this.text(speed * run_count);
            run_count++;
        } else if(parseInt(_this.text()) < count) {
            var curr_count = parseInt(_this.text()) + 1;
            _this.text(curr_count);
        } else {
            clearInterval(int);
        }
    }, int_speed);
}

$(function(){
	$("#main-content .wrapper h1[data-name='countLabel']").each(function(){
		countUp($(this));
	});
});

