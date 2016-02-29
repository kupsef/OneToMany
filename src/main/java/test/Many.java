package test;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@javax.persistence.Entity(name="Many")
public class Many {
	@javax.persistence.ManyToOne(cascade = CascadeType.PERSIST)
	protected One m;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long primaryKey;

	public One getOne() {
		return m;
	}

	public void setOne(One m) {
		this.m = m;
		this.m.getMany().add(this);
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
		return m != null ? m.equals(many.m) : many.m == null;

	}

	@Override
	public int hashCode() {
		int result = m != null ? m.hashCode() : 0;
		result = 31 * result + (int) (primaryKey ^ (primaryKey >>> 32));
		return result;
	}
}
