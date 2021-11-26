include ../Makefile.common

# targets for recognition and parsing
.PHONY: testrecognize testparse testdefbeforeuse
# targets for transformations
.PHONY: testdesugar testelaborate testsplit testsynthesize
# targets for cleaning
.PHONY: clean cleanparse cleandesugar cleanelaborate cleansplit cleansynthesize
# real suffixes
.SUFFIXES: .vhd 
# fake suffixes used to name the output of tests
.SUFFIXES: .vhdr .vhdp .vhdd .vhdrb .vhdpb .vhddb .diff

PARSEDIR=parb.out/parse
DESUGARDIR=parb.out/desugared
ELABORATEDIR=parb.out/elaborated
SPLITDIR=parb.out/split
SYNTHDIR=parb.out/synthesize
$(shell mkdir -p $(PARSEDIR))
$(shell mkdir -p $(DESUGARDIR))
$(shell mkdir -p $(ELABORATEDIR))
$(shell mkdir -p $(SPLITDIR))
$(shell mkdir -p $(SYNTHDIR))

VHDSRC = $(shell find . -maxdepth 1 -type f -name '*.vhd' | sort) 
BADVHDSRC = $(shell find ungrammatical -maxdepth 1 -type f -name '*.vhd' | sort) 
BADVHDUSE = $(shell find undefined-var -maxdepth 1 -type f -name '*.vhd' | sort)


help: commonhelp 
	@echo "select a target:"
	@echo "    testrecognize    -- run recognizer on all .vhd files"
	@echo "    testparse        -- run parser on all .vhd files"
	@echo "    testdefbeforeuse -- run define before use checker on all .vhd files, compare output"
	@echo "    testdesugar      -- run desugarer on all .vhd files, compare output"
	@echo "    testelaborate    -- run elaborator on all .vhd files, compare output"
	@echo "    testsplit        -- run splitter on all .vhd files, compare output"
	@echo "    testsynthesize   -- run synthesizer on all .vhd files, compare output"
	@echo "    clean            -- clean all generated files"
	@echo "    cleanparse       -- clean all parse output files"
	@echo "    cleandesugar     -- clean all desugared .vhd files"


########################################################################
#### RECOGNIZE & PARSE #################################################
########################################################################
ParseVHD = classes $(addprefix parb.out/parse/,$(VHDSRC:.vhd=.vhdp))
ParseVHDdiff = $(ParseVHD:.vhdp=.diff)

testrecognize: classes $(VHDSRC:.vhd=.vhdr) $(BADVHDSRC:.vhd=.vhdrb)
	@echo success!

testparse: classes $(ParseVHD) $(ParseVHDdiff) $(BADVHDSRC:.vhd=.vhdpb)
	@echo success!

testdefbeforeuse: classes $(VHDSRC:.vhd=.vhdd) $(BADVHDUSE:.vhd=.vhddb)
	@echo success!    

%.vhdr: %.vhd
	$(JVM) ece351.vhdl.VRecognizer $(ARGS) $<

%.vhdrb: %.vhd
	! $(JVM) ece351.vhdl.VRecognizer $(ARGS) $<

parb.out/parse/%.vhdp: %.vhd
	$(JVM) ece351.vhdl.VParser $(ARGS) $< > $@

parb.out/parse/%.diff : parb.out/parse/%.vhdp soln.out/parse/%.vhdp 
	nice diff $(DIFFFLAGS) $^

%.vhdpb: %.vhd
	! $(JVM) ece351.vhdl.VParser $(ARGS) $< > $@

%.vhdd: %.vhd
	$(JVM) ece351.vhdl.DefBeforeUseChecker $(ARGS) $<

%.vhddb: %.vhd
	! $(JVM) ece351.vhdl.DefBeforeUseChecker $(ARGS) $<

cleanparse: 
	rm -rf parb.out/parse/*


########################################################################
#### DESUGAR ###########################################################
########################################################################

DesugarVHD = classes $(addprefix parb.out/desugared/,$(VHDSRC))
DesugarVHDdiff = $(DesugarVHD:.vhd=.diff)

testdesugar: $(DesugarVHD) $(DesugarVHDdiff)
	@echo success!

parb.out/desugared/%.vhd : %.vhd
	$(JVM) ece351.vhdl.DeSugarer $(ARGS) $< > $@

parb.out/desugared/%.diff : parb.out/desugared/%.vhd soln.out/desugared/%.vhd 
	nice diff $(DIFFFLAGS) $^

cleandesugar: 
	rm -rf parb.out/desugared/*

########################################################################
#### ELABORATE #########################################################
########################################################################

ElaborateVHD = classes $(addprefix parb.out/elaborated/,$(VHDSRC))
ElaborateVHDdiff = $(ElaborateVHD:.vhd=.diff)

testelaborate: $(ElaborateVHD) $(ElaborateVHDdiff)
	@echo success!

parb.out/elaborated/%.vhd : %.vhd
	$(JVM) ece351.vhdl.Elaborator $(ARGS) $< > $@

parb.out/elaborated/%.diff : parb.out/elaborated/%.vhd soln.out/elaborated/%.vhd 
	nice diff $(DIFFFLAGS) $^

cleanelaborate: 
	rm -rf parb.out/elaborated/*

########################################################################
#### PROCESS SPLIT #####################################################
########################################################################

SplitVHD = classes $(addprefix parb.out/split/,$(VHDSRC))
SplitVHDdiff = $(SplitVHD:.vhd=.diff)

testsplit: $(SplitVHD) $(SplitVHDdiff)
	@echo success!

parb.out/split/%.vhd : %.vhd
	$(JVM) ece351.vhdl.Splitter $(ARGS) $< > $@

parb.out/split/%.diff : parb.out/split/%.vhd soln.out/split/%.vhd 
	nice diff $(DIFFFLAGS) $^

cleansplit: 
	rm -rf parb.out/split/*

########################################################################
#### SYNTHESIZE ########################################################
########################################################################

SynthesizeVHD = classes $(addprefix parb.out/synthesize/,$(VHDSRC:.vhd=.f))
SynthesizeVHDdiff = $(SynthesizeVHD:.f=.diff)

testsynthesize: $(SynthesizeVHD) $(SynthesizeVHDdiff)
	@echo success!

parb.out/synthesize/%.f : %.vhd
	$(JVM) ece351.vhdl.Synthesizer $(ARGS) $< > $@

parb.out/synthesize/%.diff : parb.out/synthesize/%.f soln.out/synthesize/%.f
	nice diff $(DIFFFLAGS) $^

cleansynthesize: 
	rm -rf parb.out/synthesize/*

########################################################################
#### CLEAN #############################################################
########################################################################
clean: cleanparse cleandesugar cleanelaborate cleansplit cleansynthesize

