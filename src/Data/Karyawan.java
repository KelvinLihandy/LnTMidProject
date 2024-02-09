package Data;

public class Karyawan {
	private String nama;
	private String jenisKelamin;
	private String jabatan;
	private String idKaryawan;
	private double gaji;
	
	public Karyawan(String nama, String jenisKelamin, String jabatan, String idKaryawan, double gaji) {
		super();
		this.setNama(nama);
		this.setJenisKelamin(jenisKelamin);
		this.setJabatan(jabatan);
		this.setIdKaryawan(idKaryawan);
		this.setGaji(gaji);
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public double getGaji() {
		return gaji;
	}

	public void setGaji(double gaji) {
		this.gaji = gaji;
	}

	public String getIdKaryawan() {
		return idKaryawan;
	}

	public void setIdKaryawan(String idKaryawan) {
		this.idKaryawan = idKaryawan;
	}
	
}
