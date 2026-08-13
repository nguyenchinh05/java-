package lab3.bai8;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class studentTableModel extends AbstractTableModel {

    private final String[] columnNames = {
            "STT",
            "Mã sinh viên",
            "Họ tên",
            "Điểm trung bình",
            "Xếp loại"
    };

    private final List<student> students = new ArrayList<>();

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        student student = students.get(rowIndex);

        switch (columnIndex) {

            case 0:
                return rowIndex + 1;

            case 1:
                return student.getMaSV();

            case 2:
                return student.getHoTen();

            case 3:
                return student.getDiemTB();

            case 4:
                return student.getXepLoai();

            default:
                return "";
        }
    }

    // Thêm sinh viên
    public void addStudent(student student) {

        students.add(student);

        fireTableDataChanged();
    }

    // Lấy sinh viên theo dòng
    public student getStudent(int row) {

        return students.get(row);
    }

    // Sửa sinh viên
    public void updateStudent(int row, student student) {

        students.set(row, student);

        fireTableDataChanged();
    }

    // Xóa sinh viên
    public void removeStudent(int row) {

        students.remove(row);

        fireTableDataChanged();
    }

    // Kiểm tra mã sinh viên đã tồn tại
    public boolean existsMaSV(String maSV) {

        for (student student : students) {

            if (student.getMaSV().equalsIgnoreCase(maSV)) {
                return true;
            }
        }

        return false;
    }

    // Kiểm tra mã khi sửa
    public boolean existsMaSVExceptRow(String maSV, int exceptRow) {

        for (int i = 0; i < students.size(); i++) {

            if (i != exceptRow
                    && students.get(i)
                    .getMaSV()
                    .equalsIgnoreCase(maSV)) {

                return true;
            }
        }

        return false;
    }
}