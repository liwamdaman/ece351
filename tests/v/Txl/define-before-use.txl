include "vhdl.grm"

rule main
	replace $ [program]
		p[program]
	by
		p[init] [processEntity] [processArchitecture] [check]
end rule

function init
	replace [program]
		p[program]
	construct em [empty]
	construct entityId [id]
		_
	construct architectureId [repeat id]
		em
	export entityId
	export architectureId
	by
		p
end function

rule processEntity
	replace $ [entity_declaration]
		E [entity_declaration]
	by
		E [processIdentifier_list]
end rule

rule processIdentifier_list
	replace $ [identifier_list]
		i [identifier_list]
	deconstruct i
		listId[list id]
	import entityId [id]
	export entityId
		entityId [processListId listId]
	by
		i
end rule

function processListId listId[list id]
	replace [id]
		i1 [id]
	deconstruct listId
		first [id] ', rest [list id]
	construct i2 [id]
		i1 [+ first]
	by
		i2 [processListId rest]
end function

rule processArchitecture
	replace $ [architecture_body]
		A [architecture_body]
	by
		A [processTarget] [processSensitivity_list] [processLiteral]
end rule

rule processSensitivity_list
	replace $ [sensitivity_list]
		S [sensitivity_list]
	by
		S [processName]
end rule

rule processName
	replace $ [name]
		n [name]
	deconstruct n
		i [id]
	import architectureId [repeat id]
	export architectureId
		architectureId[.i]
	by
		T
end rule

rule processTarget
	replace $ [target]
		t [target]
	deconstruct t
		n [name]
	deconstruct n
		i [id]
	import architectureId [repeat id]
	export architectureId
		architectureId[.i]
	by
		T
end rule

rule processLiteral
	replace $ [literal]
		l [literal]
	deconstruct l
		i1 [id]
	import architectureId [repeat id]
	export architectureId
		architectureId[.i1]
	by
		l
end rule

rule check
	replace $ [program]
		p [program]
	import architectureId [repeat id]
	import entityId [id]
	by
		p [compare architectureId entityId]
end rule

function compare architectureId [repeat id] entityId [id]
	replace [program]
		p [program]
	deconstruct architectureId
		first [id] rest [repeat id]
	by
		p [changeProgram first entityId] [compare rest entityId]
end function

function changeProgram first [id] entityId [id]
	replace [program]
		p [program]
	where not entityId [grep first]
	by
		'error 'var first 'used 'without 'definition
end function
