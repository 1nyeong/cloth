function selectAll(selectAll)  {
  const checkboxes 
       = document.getElementsByName('agree_service_chk');
  
  checkboxes.forEach((checkbox) => {
    checkbox.checked = selectAll.checked;
  })
}