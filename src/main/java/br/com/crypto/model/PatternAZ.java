package br.com.crypto.model;

public enum PatternAZ {

	A(10),
	B(11),
	C(12),
	D(13),
	E(14),
	F(15),
	G(16),
	H(17),
	I(18),
	J(19),
	K(20),
	L(21),
	M(22),
	N(23),
	O(24),
	P(25),
	Q(26),
	R(27),
	S(28),
	T(29),
	U(30),
	V(31),
	X(32),
	W(33),
	Y(34),
	Z(35),
	a(36),
	b(37),
	c(38),
	d(39),
	e(40),
	f(41),
	g(42),
	h(43),
	i(44),
	j(45),
	k(46),
	l(47),
	m(48),
	n(49),
	o(50),
	p(51),
	q(52),
	r(53),
	s(54),
	t(55),
	u(56),
	v(57),
	x(58),
	w(59),
	y(60),
	z(61);
	
	private int valor;
	
	PatternAZ(int valor) {
		this.valor = valor;
	}
	
	public int getValor(){
		return this.valor;
	}
	
}
