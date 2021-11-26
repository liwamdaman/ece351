full_adderCout <= ((((( not ( full_adderA ) ) and full_adderB) or (( not ( full_adderB ) ) and full_adderA)) and full_adderCin) or (full_adderA and full_adderB));

full_adderS <= ((( not ( ((( not ( full_adderA ) ) and full_adderB) or (( not ( full_adderB ) ) and full_adderA)) ) ) and full_adderCin) or (((( not ( full_adderA ) ) and full_adderB) or (( not ( full_adderB ) ) and full_adderA)) and ( not ( full_adderCin ) )));
