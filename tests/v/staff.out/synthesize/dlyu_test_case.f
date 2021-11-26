condition1 <= ( ( not ( ( DanWuzHereS and ( not '0' ) ) or ( ( not DanWuzHereS ) and '0' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO0 <= ( condition1 and ( DanWuzHereI0 ) ) or ( ( not condition1 ) and ( DanWuzHereI1 ) );
condition2 <= ( ( not ( ( DanWuzHereS and ( not '0' ) ) or ( ( not DanWuzHereS ) and '0' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO1 <= ( condition2 and ( DanWuzHereI2 ) ) or ( ( not condition2 ) and ( DanWuzHereI3 ) );
condition3 <= ( ( not ( ( DanWuzHereS and ( not '0' ) ) or ( ( not DanWuzHereS ) and '0' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO2 <= ( condition3 and ( DanWuzHereI4 ) ) or ( ( not condition3 ) and ( DanWuzHereI5 ) );
condition4 <= ( ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO3 <= ( condition4 and ( DanWuzHereI6 ) ) or ( ( not condition4 ) and ( DanWuzHereI7 ) );
condition5 <= ( ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO4 <= ( condition5 and ( DanWuzHereI8 ) ) or ( ( not condition5 ) and ( DanWuzHereI9 ) );
condition6 <= ( ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) ) and ( not ( ( DanWuzHereT and ( not '1' ) ) or ( ( not DanWuzHereT ) and '1' ) ) ) );
DanWuzHereO5 <= ( condition6 and ( DanWuzHereI10 ) ) or ( ( not condition6 ) and ( DanWuzHereI11 ) );

