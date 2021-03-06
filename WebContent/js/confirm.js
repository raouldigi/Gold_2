/*
 * SimpleModal Confirm Modal Dialog
 * http://simplemodal.com
 *
 * Copyright (c) 2013 Eric Martin - http://ericmmartin.com
 *
 * Licensed under the MIT license:
 *   http://www.opensource.org/licenses/mit-license.php
 */

//jQuery(function ($) {
//	$('#confirm-dialog input.confirm, #confirm-dialog a.confirm').click(function (e) {
//		e.preventDefault();
//
//		// example of calling the confirm function
//		// you must use a callback function to perform the "yes" action
//		
//		confirm("", function () {
//			document.getElementById("confermato").setAttribute("value", confermato);
//			document.getElementById("foglioForm").submit();
//		});
//	});
//});

function confirm(message, callback) {
	$('#confirm').modal({
		closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
		position: ["20%",],
		overlayId: 'confirm-overlay',
		containerId: 'confirm-container', 
		onShow: function (dialog) {
			var modal = this;

			$('.message', dialog.data[0]).append(message);

			// if the user clicks "yes"
			$('.yes', dialog.data[0]).click(function () {
				// call the callback
				if ($.isFunction(callback)) {
					callback.apply();
				}
				// close the dialog
				modal.close(); // or $.modal.close();
			});
		}
	});
}

function infoPopup(message) {
	$(document).ready(function() {
		$('#infoDiv').modal({
			closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
			position: ["20%",],
			overlayId: 'confirm-overlay',
			containerId: 'confirm-container', 
			onShow: function (dialog) {
				var modal = this;

				$('.message', dialog.data[0]).append(message);

			}
		});
		
	});
}

function errorPopup(message) {
	$(document).ready(function() {
		$('#errorDiv').modal({
			closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
			position: ["20%",],
			overlayId: 'confirm-overlay',
			containerId: 'confirm-container', 
			onShow: function (dialog) {
				var modal = this;

				$('.message', dialog.data[0]).append(message);

			}
		});
		
	});
}

//function errorDbPopup(message) {
//	$(document).ready(function() {
//		$('#errorDbDiv').modal({
//			closeHTML: "<a href='#' title='Close' class='modal-close'>x</a>",
//			position: ["20%",],
//			overlayId: 'confirm-overlay',
//			containerId: 'confirm-container', 
//			onShow: function (dialog) {
//				var modal = this;
//
//				$('.message', dialog.data[0]).append(message);
//
//			}
//		});
//	});
//}