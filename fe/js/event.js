var url = 'http://localhost:8080/todo';


$('.new-todo').on('change', function() {
	var val = $('.new-todo').val();
	var data = { 'todo' : val }; 
	if(val != "") doPost(url, data);
});


$('.todo-list').on('click', "input[type='checkbox']", function() {
	var val = $(this).parent();
	doDone(url,val);
});


$('.todo-list').on('click', "button", function() {
	var val = $(this).parent();
	var target = val.get(0).id;
	var myUrl = url + '/' + target;
	doDelete(myUrl, val);
});


$('#All').on('click', function() {
	doGet(url, 'All');
});


$('#Active').on('click', function() {
	doGet(url, 'Active');
});


$('#Completed').on('click', function() {
	doGet(url, 'Completed');
});


$('.clear-completed').on('click', function() {
	doDelete(url, '');		
});
