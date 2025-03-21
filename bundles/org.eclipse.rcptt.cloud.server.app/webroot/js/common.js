/********************************************************************************
 * Copyright (c) 2011 Xored Software Inc and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Xored Software Inc - initial API and implementation
 ********************************************************************************/

function createDatePicker(element) {
	element.DatePicker({
		format:'Y.m.d',
		date: element.val(),
		current: element.val(),
		starts: 1,
		position: 'r',
		onBeforeShow: function() {
			element.DatePickerSetDate(element.val(), true);
		},
		onChange: function(formated, dates) {
			element.val(formated);
			element.DatePickerHide();
		}
	});
}
