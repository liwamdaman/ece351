full_adderS <= ( ( ( ( full_adderA and ( not full_adderB ) ) or ( ( not full_adderA ) and full_adderB ) ) and ( not full_adderCin ) ) or ( ( not ( ( full_adderA and ( not full_adderB ) ) or ( ( not full_adderA ) and full_adderB ) ) ) and full_adderCin ) );
full_adderCout <= ( ( ( ( full_adderA and ( not full_adderB ) ) or ( ( not full_adderA ) and full_adderB ) ) and full_adderCin ) or ( full_adderA and full_adderB ) );

