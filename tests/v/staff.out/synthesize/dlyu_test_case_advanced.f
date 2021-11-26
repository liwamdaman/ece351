condition1 <= ( not ( ( DanWuzHereS and ( not DanWuzHereT ) ) or ( ( not DanWuzHereS ) and DanWuzHereT ) ) );
DanWuzHereO0 <= ( condition1 and ( DanWuzHereI0 ) ) or ( ( not condition1 ) and ( DanWuzHereI1 ) );
condition2 <= ( not ( ( DanWuzHereS and ( not DanWuzHereT ) ) or ( ( not DanWuzHereS ) and DanWuzHereT ) ) );
DanWuzHereO1 <= ( condition2 and ( DanWuzHereI1 ) ) or ( ( not condition2 ) and ( DanWuzHereI2 ) );
condition3 <= ( not ( ( DanWuzHereS and ( not DanWuzHereT ) ) or ( ( not DanWuzHereS ) and DanWuzHereT ) ) );
DanWuzHereO2 <= ( condition3 and ( ( DanWuzHereI0 and DanWuzHereI3 ) ) ) or ( ( not condition3 ) and ( ( DanWuzHereI0 or DanWuzHereI1 ) ) );
DanWuzHereO7 <= ( ( DanWuzHereJ0 and ( not DanWuzHereJ1 ) ) or ( ( not DanWuzHereJ0 ) and DanWuzHereJ1 ) );
DanWuzHereO8 <= ( not ( DanWuzHereJ1 and DanWuzHereJ0 ) );
condition4 <= ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) );
DanWuzHereO3 <= ( condition4 and ( DanWuzHereI0 ) ) or ( ( not condition4 ) and ( DanWuzHereI1 ) );
condition5 <= ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) );
DanWuzHereO4 <= ( condition5 and ( DanWuzHereI1 ) ) or ( ( not condition5 ) and ( DanWuzHereI2 ) );
condition6 <= ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) );
DanWuzHereO5 <= ( condition6 and ( ( DanWuzHereI2 and DanWuzHereI3 ) ) ) or ( ( not condition6 ) and ( ( DanWuzHereS or DanWuzHereT ) ) );
condition7 <= ( not ( ( DanWuzHereS and ( not '1' ) ) or ( ( not DanWuzHereS ) and '1' ) ) );
DanWuzHereO6 <= ( condition7 and ( DanWuzHereS ) ) or ( ( not condition7 ) and ( '0' ) );

