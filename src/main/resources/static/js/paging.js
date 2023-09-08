function pagingFormSubmit(curruntPage) {
			
    let form = document.getElementById('pagingForm');
    let page = document.getElementById('page');
    page.value = curruntPage;
    
    form.submit();
}