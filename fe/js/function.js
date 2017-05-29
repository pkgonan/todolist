function doPost(myUrl, data){
	$.ajax({
		type : 'POST',
		url : myUrl,
		data : data,
		success : function(todo){
			if($('#All').hasClass('selected') || $('#Active').hasClass('selected')){ 	
				$('.todo-list').append('<li><div class="view" id="' + todo.id + '"><input class="toggle" type="checkbox"><label>' + todo.todo + '</label><button class="destroy"></button></div></li>');
			}
			
			$('.todo-count').attr('strong', todo.length);
			$('.new-todo').val('');
			var cnt = Number($('.todo-count').children('strong').text())+1;
			$('.todo-count').children('strong').text(cnt);
		}
	});
}


function doGet(myUrl, filter){
	$.ajax({
		type : 'GET',
		url : myUrl,
		success : function(todo){
			$('.todo-list').empty();
			$('.todo-count').children('strong').text(todo.length);
			doCheck(filter);
			
			todo = doFilter(todo, filter);

			$.each(todo, function(){
				var now = $(this).get(0);
				var text;
				if(now.completed == 1){
					text = '<li class="completed"><div class="view" id="' + now.id + '"><input class="toggle" type="checkbox" checked="checked"><label>' + now.todo + '</label><button class="destroy"></button></div></li>';
				}
				else{
					text = '<li><div class="view" id="' + now.id + '"><input class="toggle" type="checkbox"><label>' + now.todo + '</label><button class="destroy"></button></div></li>';
				}
				$('.todo-list').append(text);
			});
		}
	});
}


function doFilter(todo, filter){
	todo = todo.filter(function(now) {
		var isCompleted = now.completed;

		if(filter == 'Active') return ((isCompleted == 0) == true);
		else if(filter == 'Completed') return ((isCompleted == 1) == true);
		else return true;
	});
	return todo;
}


function doCheck(filter){
	if(filter == 'All'){
		$('#All').attr('class', 'selected');
		$('#Active').attr('class', '');
		$('#Completed').attr('class', '');
	}
	else if(filter == 'Active'){
		$('#All').attr('class', '');
		$('#Active').attr('class', 'selected');
		$('#Completed').attr('class', '');
	}
	else{
		$('#All').attr('class', '');
		$('#Active').attr('class', '');
		$('#Completed').attr('class', 'selected');
	}
}


function doDone(myUrl, data){
	var id = data.get(0).id;
	var chkbox = data.children('input');
	var grandparent = data.parent();
	var data = { 'id' : id };
	$.ajax({
		type : 'PUT',
		url : myUrl,
		data : data,
		success : function(res){
			if(res == 1){
				if(chkbox.is(':checked')){
					chkbox.attr('checked', true);
					grandparent.attr('class', 'completed');
				}
				else{
					chkbox.attr('checked', false);
					grandparent.attr('class', '');
				}
			}
		}	
	});
}


function doDelete(myUrl, data){
	$.ajax({
		type : 'DELETE',
		url : myUrl,
		success : function(res){
			if(res >= 1){
				if(data != '') data.parent().remove();
				var cnt = Number($('.todo-count').children('strong').text())-1;
				$('.todo-count').children('strong').text(cnt);

				if($('#All').hasClass('selected')) doGet(url, 'All');
				else if($('#Active').hasClass('selected')) doGet(url, 'Active');
				else doGet(url, 'Completed');
			}
		}
	});
}
