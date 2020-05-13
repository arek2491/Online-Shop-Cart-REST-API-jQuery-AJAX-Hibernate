$(document).ready(function() {

  var apiRoot = 'http://localhost:8080/v1/';
  var datatableRowTemplate = $('[data-datatable-row-template]').children()[0];
  var endprice = $('[data-cart-endprice-section]').children()[0];
  var itemsContainer = $('[data-items-container]');
  var cartContainer = $('[data-cart-container]');

  // init
  getAllItems();
  getCart();
  getPrice();



  function createElement(data) {
    var element = $(datatableRowTemplate).clone();

    element.attr('data-item-id', data.id);
    element.find('[data-name-section] [data-name-paragraph]').text(data.name);
    element.find('[data-name-section] [data-name-input]').val(data.name);

    element.find('[data-price-section] [data-price-paragraph]').text(data.price);
    element.find('[data-price-section] [data-price-input]').val(data.price);

    element.find('[data-quantity-section] [data-quantity-paragraph]').text(data.quantity);
    element.find('[data-quantity-section] [data-quantity-input]').val(data.quantity);

    return element;
  }


  function handleDatatableRender(data) {
    itemsContainer.empty();
    data.forEach(function(item) {
      createElement(item).appendTo(itemsContainer);
    });
  }

  function handleDatatableCartRender(data) {
    cartContainer.empty();
    data.forEach(function(cart) {
      createElement(cart).appendTo(cartContainer);
    });
  }

  function getAllItems() {
    var requestUrl = apiRoot + 'items';

    $.ajax({
      url: requestUrl,
      method: 'GET',
      success: handleDatatableRender
    });
  }

  function getCart() {
    var requestUrl = apiRoot + 'cart';

    $.ajax({
      url: requestUrl,
      method: 'GET',
      success: handleDatatableCartRender
    });
  }

  function deleteFromCart() {
    var parentEl = $(this).parent().parent();
    var itemId = parentEl.attr('data-item-id');
    var requestUrl = apiRoot + 'cart';

    $.ajax({
      url: requestUrl + '/' + itemId,
      method: 'DELETE',
      success: function() {
        parentEl.slideUp(400, function() { parentEl.remove(); });
        getPrice();
      }
    })
  }

  function getPrice() {
    var requestUrl = apiRoot + '/getPrice';

    $.getJSON(requestUrl, function(price) {
      $('#priceResponse').text(price);
    });
  }

  function save(event) {
    event.preventDefault();
    var parentEl = $(this).parent().parent();

    var name = parentEl.find('[data-name-paragraph]').text();
    var quantity = parentEl.find('[name = quantity]').val();
    var price = parentEl.find('[data-price-paragraph]').text();



    var requestUrl = apiRoot + 'cart';

    $.ajax({
      url: requestUrl,
      method: 'POST',
      processData: false,
      contentType: "application/json; charset=utf-8",
      dataType: 'json',
      data: JSON.stringify({

        name: name,
        quantity: quantity,
        price: price


      }),
      complete: function(data) {
        if(data.status === 200) {
          getCart();
          getPrice();
        }
      }
    });
  }


  function toggleEditingState() {
    var parentEl = $(this).parent().parent();
    parentEl.toggleClass('datatable__row');

    var name = parentEl.find('[data-name-paragraph]').text();
    var quantity = parentEl.find('[data-quantity-paragraph]').text();
    var price = parentEl.find('[data-price-paragraph]').text();

    parentEl.find('[data-cart-name-paragraph]').val(name);
    parentEl.find('[data-cart-price-paragraph]').val(price);
    parentEl.find('[data-cart-quantity-paragraph]').val(quantity);
  }



  itemsContainer.on('click', '[data-item-add-button]', save);
  cartContainer.on('click', '[data-item-delete-button]', deleteFromCart);

});