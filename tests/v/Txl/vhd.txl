include "vhdl.grm"

rule main
	replace $ [program]
		p[program]
	by
		p [init] [processConditional_signal_assignment1] [processConditional_signal_assignment2]
end rule

function init
	replace $ [program]
		p [program]
	construct variableId [repeat id]
		XA XB XC XD XE XF XG XH XI XJ XK XL XM XN
	export variableId
	by
		p
end function

rule processConditional_signal_assignment1
	replace [conditional_signal_assignment]
		csa [conditional_signal_assignment]
	deconstruct csa
		t [target] '<= cw [conditional_waveforms] ';
	deconstruct cw
		w [waveform]
	deconstruct w
		we [waveform_element]
	deconstruct we
		e1 [expression]
	deconstruct e1
		r1 [relation] lor [logical_operator_relation]
	deconstruct r1
		'( e2 [expression] ')
	construct x [id]
		'x
	construct i [id]
		x [processVariableId]
	by
		t '<= i lor ';
		i '<= e2 ';
end rule

rule processConditional_signal_assignment2
	replace [conditional_signal_assignment]
		csa [conditional_signal_assignment]
	deconstruct csa
		t [target] '<= cw [conditional_waveforms] ';
	deconstruct cw
		w [waveform]
	deconstruct w
		we [waveform_element]
	deconstruct we
		e1 [expression]
	deconstruct e1
		r1 [relation] lor [logical_operator_relation]
	deconstruct lor
		lo [logical_operator] r2 [relation]
	deconstruct r2
		'( e2 [expression] ')
	construct x [id]
		'x
	construct i [id]
		x[processVariableId]
	by
		t '<= r1 lo i ';
		i '<= e2 ';
end rule

function processVariableId
	replace $ [id]
		i [id]
	import variableId [repeat id]
	deconstruct variableId
		first [id] rest [repeat id]
	export variableId
		rest
	by
		first
end function




