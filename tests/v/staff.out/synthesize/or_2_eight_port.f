OR_gate_2F <= ( OR_gate_2x or OR_gate_2y );
four_port_OR_gate_2result <= ( four_port_OR_gate_2e or four_port_OR_gate_2f );
four_port_OR_gate_2e <= ( four_port_OR_gate_2a or four_port_OR_gate_2b );
four_port_OR_gate_2f <= ( four_port_OR_gate_2c or four_port_OR_gate_2d );
eight_port_OR_gate_2y <= ( eight_port_OR_gate_2result1 or eight_port_OR_gate_2result2 );
eight_port_OR_gate_2result1 <= ( eight_port_OR_gate_2comp3_e or eight_port_OR_gate_2comp3_f );
eight_port_OR_gate_2comp3_e <= ( eight_port_OR_gate_2x0 or eight_port_OR_gate_2x1 );
eight_port_OR_gate_2comp3_f <= ( eight_port_OR_gate_2x2 or eight_port_OR_gate_2x3 );
eight_port_OR_gate_2result2 <= ( eight_port_OR_gate_2comp4_e or eight_port_OR_gate_2comp4_f );
eight_port_OR_gate_2comp4_e <= ( eight_port_OR_gate_2x4 or eight_port_OR_gate_2x5 );
eight_port_OR_gate_2comp4_f <= ( eight_port_OR_gate_2x6 or eight_port_OR_gate_2x7 );

