package test;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity(name="Many")
public class Many {
	@javax.persistence.ManyToOne(cascade = CascadeType.PERSIST)
	protected One one;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long primaryKey;

	public One getOne() {
		return one;
	}

	public void setOne(One one) {
		this.one = one;
		this.one.getMany().add(this);
	}

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Many many = (Many) o;

		if (primaryKey != many.primaryKey)
			return false;
		return one != null ? one.equals(many.one) : many.one == null;

	}

	@Override
	public int hashCode() {
		int result = one != null ? one.hashCode() : 0;
		result = 31 * result + (int) (primaryKey ^ (primaryKey >>> 32));
		return result;
	}
}
