class Matrix3 {

	double[] V;

	public Matrix3(double[] values) {
		this.V = values;

	}

	public double[] multiply(double[] I) {
		double[] result = { 0, 0, 0 };

		result[0] = ((V[0] * I[0]) + (V[1] * I[1]) + (V[2] * I[2]));
		result[1] = ((V[3] * I[0]) + (V[4] * I[1]) + (V[5] * I[2]));
		result[2] = ((V[6] * I[0]) + (V[7] * I[1]) + (V[8] * I[2]));

		return result;

	}

}