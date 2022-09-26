import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import { Button, Grid, ThemeProvider } from "@mui/material";

import "styles/common/CustomModal.scss";
import theme from "components/common/theme";

function CustomModal({
  open,
  setOpen,
  title,
  content,
  cancelMessage,
  okMessage,
  handleClickButton,
}) {
  const handleCancel = () => {
    setOpen(false);
  };

  const handleOK = () => {
    setOpen(false);
    // handleClickButton();
  };

  return (
    <Modal
      className="custom-modal"
      open={open}
      onClose={handleCancel}
      closeAfterTransition
      BackdropComponent={Backdrop}
      BackdropProps={{
        timeout: 500,
      }}
    >
      <Fade in={open}>
        <Box>
          <Grid
            container
            direction="column"
            justifyContent="center"
            textAlign="center"
            flexWrap="nowrap"
          >
            <Grid className="custom-modal__title" item>
              <h2>{title}</h2>
            </Grid>
            <Grid className="custom-modal__content" item>
              {content.split("\\n").map((item) => (
                <p>{item}</p>
              ))}
            </Grid>
            <Grid className="custom-modal__button" item>
              <Button
                className="custom-modal__button--cancel"
                variant="outlined"
                onClick={handleCancel}
              >
                {cancelMessage}
              </Button>
              <ThemeProvider theme={theme}>
                <Button
                  className="custom-modal__button--ok"
                  variant="contained"
                  onClick={handleOK}
                >
                  {okMessage}
                </Button>
              </ThemeProvider>
            </Grid>
          </Grid>
        </Box>
      </Fade>
    </Modal>
  );
}

export default CustomModal;