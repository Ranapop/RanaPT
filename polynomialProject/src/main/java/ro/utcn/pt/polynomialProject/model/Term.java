package ro.utcn.pt.polynomialProject.model;

import ro.utcn.pt.polynomialProject.model.exceptions.InputFormatException;

public class Term{
	
	private Double coefficient;
	private int degree;
	
	public Double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}

	public Term(Double coefficient, int degree){
		setCoefficient(coefficient);
		setDegree(degree);
	}
	
	//Constructor that takes an argument as a String
	public Term(String termAsString) throws InputFormatException{
		Double coefficient;
		int power;
		try{
			String[] parts = termAsString.split("x\\^");
			if(parts.length == 1){
				coefficient = Double.valueOf(parts[0]);
				power = 0;
			}else{
				if(parts[0].equals("")){
					coefficient = 1.;
				}else if(parts[0].equals("-")){
					coefficient = -1.;
				}else{
					coefficient = Double.valueOf(parts[0]);
				}
				power = Integer.valueOf(parts[1]);
			}
			setCoefficient(coefficient);
			setDegree(power);
		}
		catch (Exception e){
			
			throw new InputFormatException();
			
		}
		
	}
	public Term(){
		this(new Double(0),0);
	}
	
	public Term clone(){
		return new Term(this.coefficient,this.degree);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coefficient == null) ? 0 : coefficient.hashCode());
		result = prime * result + degree;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Term other = (Term) obj;
		if (coefficient == null) {
			if (other.coefficient != null)
				return false;
		} else if (!coefficient.equals(other.coefficient))
			return false;
		if (degree != other.degree)
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		String coefficientAsAString = String.valueOf(coefficient);
		//get rid of .0
		String [] parts = coefficientAsAString.split("\\.");
		if(parts[1].equals("0")){
			coefficientAsAString = coefficientAsAString.substring(0,coefficientAsAString.length()-2);
		}
		return coefficientAsAString+"x^"+String.valueOf(degree);
	}
	
}
