(function() {


	var _self = this;
	_self.interval;
	_self.cursorX;
	_self.cursorY;
	_self.newCursorX;
	_self.newCursorY;


	$(document).on("mouseenter", '.tooltip', function(event) {
		var targetElement = this;
		_self.cursorX = event.screenX;
		_self.cursorY = event.screenY;
		_self.newCursorX = event.screenX;
		_self.newCursorY = event.screenY;
		

		interval = setInterval(function(){
			checkCursorPosition();
		}, 300);

		document.addEventListener("mousemove", _self.defineNewPosition );

		checkCursorPosition = function() {
			if(_self.cursorX == _self.newCursorX && 
				_self.cursorY == _self.newCursorY) {
				var content = targetElement.querySelector('.tooltip-content');
				if(content != null)
					content.classList.remove('hidden');
			} else {
				_self.cursorX = _self.newCursorX;
				_self.cursorY = _self.newCursorY;
			}
		}
	});

	$(document).on("mouseleave", '.tooltip', function(event) {
		clearInterval(interval);
		document.removeEventListener("mousemove", _self.defineNewPosition );
		_self.hideTooltip();
	});


	_self.defineNewPosition = function(event){
		_self.hideTooltip();
		_self.newCursorX = event.screenX;
		_self.newCursorY = event.screenY;
	}

	_self.hideTooltip = function() {
		var content = document.querySelector('.tooltip-content:not(.hidden)');
		if(content != null) {
			content.classList.add('hidden');
		}
	}


})();